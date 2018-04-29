package exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Page Not Found") //404
public class CustomException extends Exception{

	private static final long serialVersionUID = 2292346834265371L;

	public CustomException( ){
		super("NotFoundException");
	}
}
