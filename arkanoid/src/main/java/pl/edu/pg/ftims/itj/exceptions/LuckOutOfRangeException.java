package pl.edu.pg.ftims.itj.exceptions;

//wyjatek pojawiajacy sie w przypadku kiedy parametr luck bedzie wiekszy od 90
@SuppressWarnings("serial")
public class LuckOutOfRangeException extends Exception {
	public String getMessage() {
		return "PARAMETER LUCK OUT OF RANGE !!! " + super.getMessage();
	}
}
