
insert into product (product_no, category, created_by, deleted_by, is_active, product_name, sell_price, seller_no, supply_price, tax_type, updated_by)
values (1, 0, now(), null, true, 'TEST 1', 10000, 1, 5000, 'TAX', now());

insert into seller (seller_no, account_no, account_owner_name, bank_type, business_no, commission, created_at,
                    default_delivery_amount, deleted_at, is_active, sell_type, seller_name, updated_at)
values (1, 1, 'owner name', 'sinha', '0001112222', 3, now(), 3000, null, 1, 0, 'PURCHASE', now());



insert into order_origin (order_no, coupon_discount_amount, created_at, deleted_at, paid_confirmed_at, paid_pg_amount,
                          updated_at, used_mileage_amount)
values (1, 0, now(), null, now(), 30000, now(), 0);

insert into order_item_snapshot (order_item_snapshot_no, default_delivery_amount, item_category, mileage_usage_amount,
                                 promotion_amount, sell_price, supply_price, tax_rate, tax_type, product_no, seller_no)
values (1, 3000, 0, 0, 0, 10000, 5000, 1, 'TAX', 1, 1);
-- 구매 확정 (purchase_confirmed_at)
insert into order_item (order_item_no, created_at, deleted_at, item_delivery_status, order_count, order_no,
                        purchase_confirmed_at, shipped_complete_at, updated_at, order_item_snapshot_no)
values (1, now(), null, 3, 4, 1, null, now(), now(), 1);

insert into order_item_snapshot (order_item_snapshot_no, default_delivery_amount, item_category, mileage_usage_amount,
                                 promotion_amount, sell_price, supply_price, tax_rate, tax_type, product_no, seller_no)
values (2, 3000, 0, 0, 0, 10000, 5000, 4, 'TAX', 1, 1);
-- 구매 확정 (purchase_confirmed_at)
insert into order_item (order_item_no, created_at, deleted_at, item_delivery_status, order_count, order_no,
                        purchase_confirmed_at, shipped_complete_at, updated_at, order_item_snapshot_no)
values (2, now(), null, 3, 1, 1, null, now(), now(), 2);



insert into order_origin (order_no, coupon_discount_amount, created_at, deleted_at, paid_confirmed_at, paid_pg_amount,
                          updated_at, used_mileage_amount)
values (2, 0, now(), null, now(), 30000, now(), 0);

insert into order_item_snapshot (order_item_snapshot_no, default_delivery_amount, item_category, mileage_usage_amount,
                                 promotion_amount, sell_price, supply_price, tax_rate, tax_type, product_no, seller_no)
values (3, 3000, 0, 0, 0, 10000, 5000, 3, 'TAX', 1, 1);

insert into order_item (order_item_no, created_at, deleted_at, item_delivery_status, order_count, order_no,
                        purchase_confirmed_at, shipped_complete_at, updated_at, order_item_snapshot_no)
values (3, now(), null, 3, 4, 2, null, now(), now(), 3);

insert into order_item_snapshot (order_item_snapshot_no, default_delivery_amount, item_category, mileage_usage_amount,
                                 promotion_amount, sell_price, supply_price, tax_rate, tax_type, product_no, seller_no)
values (4, 3000, 0, 0, 0, 10000, 5000, 4, 'TAX', 1, 1);

insert into order_item (order_item_no, created_at, deleted_at, item_delivery_status, order_count, order_no,
                        purchase_confirmed_at, shipped_complete_at, updated_at, order_item_snapshot_no)
values (4, now(), null, 3, 1, 2, null, now(), now(), 4);



-- 클레임 완료 (completed_at)
insert into claim_receipt (claim_receipt_no, claim_reason, claim_status, completed_at, created_at, deleted_at,
                           extra_fee_payer, order_no, request_type, updated_at)
values (1, 1, 2, now(), now(), null, 1, 1, 'EXCHANGE', now());
insert into claim_item (claim_item_no, claim_count, claim_receipt_no, created_at, deleted_at, order_item_no, updated_at)
values (1, 1, 1, now(), null, 1, now());