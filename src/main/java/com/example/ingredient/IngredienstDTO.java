package com.example.ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredienstDTO {


        private Long id;

        private String title;
        private Long amount;
        private Boolean isDeleted=false;

}
