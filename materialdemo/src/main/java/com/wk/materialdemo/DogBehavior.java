package com.wk.materialdemo;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ImageView;

/**
 * User: WangKai(123940232@qq.com)
 * 2016-02-18 11:23
 */
public class DogBehavior extends CoordinatorLayout.Behavior<ImageView> {
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }
}
