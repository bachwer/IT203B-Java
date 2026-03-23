DELIMITER //

CREATE PROCEDURE GET_SURGERY_FEE(
    IN surgery_id INT,
    OUT total_cost DECIMAL(10,2)
)
BEGIN
    -- ví dụ đơn giản
SELECT 1000.50 INTO total_cost;
END //

DELIMITER ;