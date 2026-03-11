package com.artesanias.model;

public class OrderItem {

}

/*
Table order_item {
  id uuid [PRIMARY KEY]
  order_id uuid [NOT null, ref: > order.id]
  product_id uuid [NOT null, ref: > product.id]
  color_id uuid [NOT null, ref: > color.id]
  quantity int [NOT null]
  price numeric(14,2)
}
*/