package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.SliderDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.SliderResponse;
import com.example.coffeebe.domain.entities.business.Slider;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class SliderService extends BaseAbtractService implements BaseService<Slider, Long> {

    @Override
    public CustomPage<Slider> findAll(Pageable pageable) {
        Page<Slider> sliderPage = sliderRepository.findAll(pageable);
        return new CustomPage<>(sliderPage);
    }

    @Override
    public Slider findById(HttpServletRequest request, Long id) {
        return getSliderById(id);
    }

    @Override
    public Slider create(HttpServletRequest request, DTO dto) {
        SliderDto sliderDto = modelMapper.map(dto, SliderDto.class);
        Slider slider = Slider.builder()
                .name(sliderDto.getName())
                .link(sliderDto.getLink())
                .status(true)
                .build();

        sliderRepository.save(slider);
        return slider;
    }

    @Override
    public Slider update(HttpServletRequest request, Long id, DTO dto) {
        SliderDto sliderDto = modelMapper.map(dto, SliderDto.class);
        Slider slider = findById(request, id);
        slider.setName(sliderDto.getName());
        slider.setLink(sliderDto.getLink());

        sliderRepository.save(slider);
        return slider;
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Slider slider = findById(request,id);

        sliderRepository.delete(slider);
        return true;
    }

    @Override
    public Page<Slider> findAllByFilter(FilterDto<Slider> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<Slider> findAllByFilter(HttpServletRequest request) {
        return null;
    }

    public SliderResponse changeStatus(Long id) {
        Slider slider = getSliderById(id);
        slider.setStatus(!slider.getStatus());

        slider = sliderRepository.save(slider);
        return modelMapper.map(slider, SliderResponse.class);
    }
}
