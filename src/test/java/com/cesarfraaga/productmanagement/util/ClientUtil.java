package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.ClientDTO;
import com.cesarfraaga.productmanagement.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientUtil {

    public static Client buildValidClient() {
        return new Client(1L, "César", "12345", "20/01/2003");
    }

    public static Client buildClientWithNullId() {
        return new Client(null, "César", "12345", "20/01/2003");
    }

    public static ClientDTO buildValidClientDTO() {
        return new ClientDTO(1L, "César", "12345", "20/01/2003");
    }

    public static ClientDTO buildClientDTOWithNullName() {
        return new ClientDTO(1L, null, "123456", "20/01/2003");
    }

    public static ClientDTO buildClientDTOWithBlankName() {
        return new ClientDTO(1L, "", "12345", "20/01/2003");
    }

    public static ClientDTO buildClientDTOWithNameLengthIsLessThanTwo(){
        return new ClientDTO(1L, "A", "12345", "20/01/2003");
    }

    public static ClientDTO buildClientDTOWithNameLengthIsLongerThanFifty(){
        return new ClientDTO(1L, "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY", "12345", "20/01/2003");
    }

    public static ClientDTO buildClientDTOWithNullCpf() {
        return new ClientDTO(1L, "César", null, "20/01/2003");
    }
    public static ClientDTO buildClientDTOWithEmptyCpf() {
        return new ClientDTO(1L, "César", "", "20/01/2003");
    }

    public static ClientDTO buildClientDTOWithNullBirthDay() {
        return new ClientDTO(1L, "César", "123456", null);
    }
    public static ClientDTO buildClientDTOWithEmptyBirthDay() {
        return new ClientDTO(1L, "César", "123456", "");
    }
}
