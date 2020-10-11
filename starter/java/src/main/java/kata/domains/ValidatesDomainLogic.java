
package kata.domains;

/**
 *
 * @author mark
 */
public interface ValidatesDomainLogic {
	public void onValidationFailure(Object source, String message);
}
