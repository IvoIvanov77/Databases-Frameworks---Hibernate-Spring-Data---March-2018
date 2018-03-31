DROP PROCEDURE IF EXISTS total_number_of_books_that_author_has_written &&

CREATE PROCEDURE total_number_of_books_that_author_has_written(IN full_name varchar(50), OUT count_of_books INTEGER(11))
BEGIN
	SET count_of_books = (SELECT count(b.id)
	FROM books AS b
	INNER JOIN authors AS a ON a.id = b.author_id
	WHERE  CONCAT(a.first_name, ' ', a.last_name) = full_name);
END $$


