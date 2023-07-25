package org.contextmapper.sample.tlas.infrastructure.api;

import org.contextmapper.sample.tlas.application.TlaApplicationService;
import org.contextmapper.sample.tlas.infrastructure.api.mapper.TlaApiDTOMapper;
import org.contextmapper.sample.tlas.infrastructure.api.model.TLADto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.contextmapper.sample.tlas.infrastructure.api.mapper.TlaApiDTOMapper.tlaToDto;

@Component
public class TlaApiDelegateImpl implements TlasApiDelegate {

    private TlaApplicationService applicationService;

    public TlaApiDelegateImpl(final TlaApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public ResponseEntity<List<TLADto>> listTLAs() {
        return ResponseEntity.ok(applicationService.findAllTLAs().stream()
                .map(TlaApiDTOMapper::tlaToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<TLADto> getTLAByName(final String name) {
        return ResponseEntity.ok(tlaToDto(applicationService.getTLAByName(name)));
    }
}
