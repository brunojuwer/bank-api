ALTER TABLE loan ADD COLUMN installments int;
ALTER TABLE loan ADD COLUMN installments_amount decimal(8, 2);
ALTER TABLE loan ADD COLUMN installment_due_date timestamptz;