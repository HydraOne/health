package cn.geny.health.bo;

import cn.geny.health.po.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/4 9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Account {
    public String photoURL;
    public String phoneNumber;
    public String country;
    public String address;
    public String city;
    public String zipCode;
    public String about;
    public String role;
}