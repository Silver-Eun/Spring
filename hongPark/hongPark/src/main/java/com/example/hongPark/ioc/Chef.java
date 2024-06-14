package com.example.hongPark.ioc;

import org.springframework.stereotype.Component;

@Component // 해당 클래스를 객체로 만들고 IoC 컨테이너에 등록
public class Chef {
    // Chef는 IngredientFactory를 알고 있음
    private IngredientFactory ingredientFactory;

    // Chef가 IngredientFactory와 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // 재료 준비
        Ingredient ingredient = ingredientFactory.get(menu);
        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
