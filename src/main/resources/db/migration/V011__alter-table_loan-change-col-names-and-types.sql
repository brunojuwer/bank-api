ALTER TABLE loan DROP COLUMN installment_due_date;
ALTER TABLE loan ADD COLUMN installments_due_day int;
ALTER TABLE loan RENAME COLUMN installments_amount TO installments_value;
