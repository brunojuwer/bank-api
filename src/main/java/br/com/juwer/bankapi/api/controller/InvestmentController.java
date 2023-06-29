package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.InvestmentAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.InvestmentDisassembler;
import br.com.juwer.bankapi.api.dto.input.InvestmentInput;
import br.com.juwer.bankapi.api.dto.input.InvestmentNewProfitability;
import br.com.juwer.bankapi.api.dto.output.InvestmentDTO;
import br.com.juwer.bankapi.domain.model.Investment;
import br.com.juwer.bankapi.domain.repository.InvestmentRepository;
import br.com.juwer.bankapi.domain.service.InvestmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentRepository repository;
    private final InvestmentService service;
    private final InvestmentAssembler investmentAssembler;
    private final InvestmentDisassembler investmentDisassembler;

    @GetMapping
    public List<InvestmentDTO> findAll() {
        return investmentAssembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{investmentId}")
    public InvestmentDTO findOne(@PathVariable Long investmentId) {
        return investmentAssembler.toModel(service.findInvestmentById(investmentId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvestmentDTO create(@Valid @RequestBody InvestmentInput investmentInput) {
        Investment investment = investmentDisassembler.toDomainModel(investmentInput);
        return investmentAssembler.toModel(service.create(investment));
    }

    @PutMapping("/{investmentId}")
    public InvestmentDTO updateProfitability(
        @PathVariable Long investmentId,
        @RequestBody @Valid InvestmentNewProfitability investmentNewProfitability
    ) {
        BigDecimal profitability = investmentNewProfitability.getProfitability();

        Investment investment = service
                .updateProfitabillity(investmentId, profitability);

        return investmentAssembler.toModel(investment);
    }

    @DeleteMapping("/{investmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long investmentId) {
        service.delete(investmentId);
    }
}