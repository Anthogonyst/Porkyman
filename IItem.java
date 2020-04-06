package Maestus.Porkyman;

/**
 * Contains functions for additional items that the player carries
 * Implements the IAction interface to pass turns
 * @author Oracle
 *
 */
public interface IItem extends IAction {

	/**
	 * Additional bag option to handle quantity manifest
	 * @return bagEmpty
	 */
	public abstract boolean depleteItem();
}
