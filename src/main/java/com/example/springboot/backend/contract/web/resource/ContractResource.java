package com.example.springboot.backend.contract.web.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class ContractResource
{
    protected long idContract;
    @NotNull
    @Positive
    protected Long insurer;
    @NotNull
    @FutureOrPresent
    protected LocalDateTime startOfInsurance;
    @NotNull
    @FutureOrPresent
    protected LocalDateTime endOfInsurance;
    @Positive
    protected double amountOfInsuranceIndemnity;
    @Positive
    protected double monthlyPayment;
}
