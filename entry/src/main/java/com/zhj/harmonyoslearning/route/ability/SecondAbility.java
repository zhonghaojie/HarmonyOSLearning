package com.zhj.harmonyoslearning.route.ability;

import com.zhj.harmonyoslearning.route.slice.SecondAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.window.dialog.ToastDialog;

import static com.zhj.harmonyoslearning.route.utils.Const.ABILITY_SECOND_RESULT_CODE;
import static com.zhj.harmonyoslearning.route.utils.Const.PARAM_SECOND_ABILITY_RESULT;

public class SecondAbility extends Ability {
    private String param = "";
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        param = intent.getStringParam("param");
        new ToastDialog(this).setText(param).show();
        super.setMainRoute(SecondAbilitySlice.class.getName());
    }

    @Override
    protected void onActive() {
        super.onActive();
        Intent intent =new Intent();
        intent.setParam(PARAM_SECOND_ABILITY_RESULT,"这是来自SecondAbility的消息");
        setResult(ABILITY_SECOND_RESULT_CODE,intent);
    }
}
