package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.OrderDto;
import com.example.coffeebe.app.dtos.request.impl.PaymentDto;
import com.example.coffeebe.app.dtos.request.impl.TransactionDto;
import com.example.coffeebe.app.dtos.request.impl.TransactionStatusDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.DataResponse;
import com.example.coffeebe.app.dtos.responses.TransactionResponse;
import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.entities.business.Discount;
import com.example.coffeebe.domain.entities.business.Order;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.entities.business.Transaction;
import com.example.coffeebe.domain.entities.data.MonthRevenue;
import com.example.coffeebe.domain.entities.enums.RoleType;
import com.example.coffeebe.domain.entities.enums.TransactionStatus;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.Constant;
import com.example.coffeebe.domain.utils.Helper;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionService extends BaseAbtractService implements BaseService<Transaction, Long> {

    @Autowired
    PaymentService paymentService;

    @Override
    public CustomPage<Transaction> findAll(Pageable pageable) {
        User user = getUser();
        Page<Transaction> transactionPage = null;
        if (user.getRole().getName().equals(RoleType.ADMIN)) {
            transactionPage = transactionRepository.findAll(pageable);
        } else {
            transactionPage = transactionRepository.findAllByUser(user.getId(), pageable);
        }
        CustomPage<Transaction> transactionCustomPage = new CustomPage<>();
        transactionCustomPage.setData(transactionPage.getContent());
        transactionCustomPage.setMetadata(new CustomPage.Metadata(transactionPage));

        return transactionCustomPage;
    }

    public List<Transaction> findAllByUser() {
        User user = getUser();
        List<Transaction> transactionPage = transactionRepository.findAllByUserId(user.getId());
        return transactionPage;
    }

    @Override
    public Transaction findById(HttpServletRequest request, Long id) {
        return getTransactionById(id);
    }

    @Override
    public Transaction create(HttpServletRequest request, DTO dto) {
        User user = getUser();
        TransactionDto transactionDto = modelMapper.map(dto, TransactionDto.class);
        List<Long> productIds = transactionDto.getOrders().parallelStream().map(OrderDto::getProductID).collect(Collectors.toList());
        List<Product> products = productRepository.findAllById(productIds);
        if (products.size() != productIds.size()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.INVALID_PRODUCT_ID);
        }

        List<Long> discountIds = transactionDto.getOrders().
                parallelStream().filter(ele -> ele.getDiscountId() != null).map(OrderDto::getDiscountId).collect(Collectors.toList());
        List<Discount> discounts = discountIds.isEmpty() ? new ArrayList<>() : discountRepository.findAllById(discountIds);

        Map<Long, Product> mapProduct = new HashMap<>();
        Map<Long, Discount> mapDiscount = new HashMap<>();
        products.forEach(ele -> {
            mapProduct.put(ele.getId(), ele);
        });
        discounts.forEach(ele -> {
            mapDiscount.put(ele.getId(), ele);
        });

        Transaction transaction = new Transaction();
        transaction.setPayment(transactionDto.getPayment());
        transaction.setUserEmail(transactionDto.getEmail());
        transaction.setUserPhone(transactionDto.getPhone());
        transaction.setAddress(transactionDto.getAddress());
        transaction.setPaymentInfo(transactionDto.getPaymentInfo());
        transaction.setUser(user);
        List<Order> orders = new ArrayList<>();
        transactionDto.getOrders().forEach(ele -> {
            Order order = new Order();
            Product product = (mapProduct.get(ele.getProductID()));
            if (ele.getQuantity() > product.getQuantity()) {
                throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.INVENTORY_NOT_ENOUGH);
            }
            order.setProduct(product);
            order.setQuantity(ele.getQuantity());
            product.setQuantity(product.getQuantity() - ele.getQuantity());
            double amount;
            if (ele.getDiscountId() != null) {
                Discount discount = mapDiscount.get(ele.getDiscountId());
                if (discount.getProduct().getId().longValue() != ele.getProductID().longValue()) {
                    throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.PRODUCT_INVALID);
                }
                Long now = (new Date()).getTime();
                if (discount.getStartDate().getTime() <= now && discount.getEndDate().getTime() >= now) {
                    amount = (double)product.getPrice() * ele.getQuantity() * (100 - discount.getDiscount()) / 100d;
                    order.setAmount(amount);
                } else {
                    throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.DISCOUNT_EXPIRED_TIME);
                }
            } else {
                amount = product.getPrice() * ele.getQuantity();
            }
            order.setAmount(amount);
            orders.add(order);
        });
        transaction.setAmount(orders.parallelStream().map(Order::getAmount).reduce(0d, Double::sum));
        double sumAmount = transaction.getAmount();
        transaction.setAmount(Helper.roundTwoDecimal(sumAmount));
        transaction.setOrderSelf(orders);
        transaction.setPayment(transactionDto.getPayment());

        transaction.setStatus(TransactionStatus.SUCCESSFUL.toString());
        productRepository.saveAll(mapProduct.values());
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction update(HttpServletRequest request, Long id, DTO dto) {
        return null;
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        return false;
    }

    @Override
    public Page<Transaction> findAllByFilter(FilterDto<Transaction> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<Transaction> findAllByFilter(HttpServletRequest request) {
        return null;
    }

    public TransactionResponse changeStatusTransaction(Long id, DTO dto) {
        TransactionStatusDto statusDto = modelMapper.map(dto, TransactionStatusDto.class);
        User user = getUser();
        Transaction transaction = getTransactionById(id);
        if ((user.getRole().getName().equals(RoleType.USER) && Constant.mapStatusUser.get(transaction.getStatus()).equals(statusDto.getStatus())) ||
                (user.getRole().getName().equals(RoleType.ADMIN) && Constant.mapStatusAdmin.get(transaction.getStatus()).equals(statusDto.getStatus()))) {
            transaction.setStatus(statusDto.getStatus());

        } else if (user.getRole().getName().equals(RoleType.ADMIN) && statusDto.getStatus().equals(TransactionStatus.CANCEL.toString()) &&
                (transaction.getStatus().equals(TransactionStatus.WAIT_FOR_APPROVE.toString()) || transaction.getStatus().equals(TransactionStatus.APPROVED.toString()))) {
            transaction.setStatus(TransactionStatus.CANCEL.toString());
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.TRANSACTION_STATUS_INCORRECT);
        }
        transaction = transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionResponse.class);
    }

    public DataResponse getRevenueByYear(int year) {
        List<MonthRevenue> listResponse = new ArrayList<>();
        List<MonthRevenue> result = transactionRepository.getRevenueInYear(year);
        Map<Integer, MonthRevenue> mapMonthRevenue = new HashMap<>();
        result.forEach(item -> {
            mapMonthRevenue.put(item.getMonth(), item);
        });
        for (int i = 1; i <= 12; i++) {
            if (mapMonthRevenue.get(i) != null) {
                listResponse.add(mapMonthRevenue.get(i));
            } else {
                listResponse.add(new MonthRevenue(i, 0d));
            }
        }
        return new DataResponse(listResponse);
    }
}