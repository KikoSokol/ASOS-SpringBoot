package com.example.springboot.backend.contract.web.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class LifeInsuranceResource extends ContractResource
{
    @NotNull
    @Positive
    protected Long insured;

}
