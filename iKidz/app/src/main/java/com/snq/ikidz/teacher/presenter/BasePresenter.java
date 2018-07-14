package com.snq.ikidz.teacher.presenter;

import com.snq.ikidz.teacher.view.activity.login.ILogin;

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
