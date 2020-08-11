package org.thraex;

import org.thraex.base.result.Result;
import org.thraex.entity.User;

/**
 * @author 鬼王
 * @date 2020/08/11 11:23
 */
public class App {

    public static void main(String[] args) {
        Result<User> ok = Result.ok(new User());
        Result<User> ok1 = Result.ok(new User(), 2001);
        Result<User> ok2 = Result.ok(new User(), 2002, "Success");
        Result fail = Result.fail("Failed");
        Result fail1 = Result.fail("Guiwang", 4001);
        System.out.println(1);
    }

}
