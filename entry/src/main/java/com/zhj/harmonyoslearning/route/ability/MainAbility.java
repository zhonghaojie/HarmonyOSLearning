package com.zhj.harmonyoslearning.route.ability;

import com.zhj.harmonyoslearning.route.slice.MainAbilitySlice;
import com.zhj.harmonyoslearning.route.slice.MainAbilitySlice2;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability   {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        addActionRoute("action.main.slice2", MainAbilitySlice2.class.getName());
    }
}
