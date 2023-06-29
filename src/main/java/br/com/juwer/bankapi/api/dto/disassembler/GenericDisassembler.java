package br.com.juwer.bankapi.api.dto.disassembler;

import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;

public abstract class GenericDisassembler <I, D> {

    protected final ModelMapper mapper;
    private final Class<D> domainObject;



    @SuppressWarnings("unchecked")
    public GenericDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();

        this.domainObject = (Class<D>) type.getActualTypeArguments()[1];
    }

    public D toDomainModel(I inputObject) {
        return this.mapper.map(inputObject, this.domainObject);
    }

    public void copyToDomainModel(I inputObject, D domainObject) {
        this.mapper.map(inputObject, domainObject);
    }
}
