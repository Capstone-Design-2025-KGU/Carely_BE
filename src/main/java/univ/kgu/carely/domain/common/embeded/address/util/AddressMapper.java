package univ.kgu.carely.domain.common.embeded.address.util;

import org.mapstruct.Mapper;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.embeded.address.ReqAddressDTO;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(ReqAddressDTO dto);

}
