package com.sproject.ikidz.presenter;

import com.sproject.ikidz.view.activity.login.ILogin;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:57 PM
 * Email: vihahb@gmail.com
 */
public class BasePresenter {

    private ILogin view;

    public BasePresenter(ILogin view) {
        this.view = view;
    }
}
