package ma.valueit.testingplatform.core.errorhandling.validator;


import ma.valueit.testingplatform.core.errorhandling.businessexception.AdvancedBusinessException;

/**
 * Created by yelansari on 3/12/18.
 */
public interface Validator {
    public ValidationMessages getValidationMessages();
    public void validate() throws AdvancedBusinessException;
}
