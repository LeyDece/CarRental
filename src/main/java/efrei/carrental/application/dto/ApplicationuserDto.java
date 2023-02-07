package efrei.carrental.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationuserDto {

    public int id;

    public String username;

    public String email;

    public String userType;

}
