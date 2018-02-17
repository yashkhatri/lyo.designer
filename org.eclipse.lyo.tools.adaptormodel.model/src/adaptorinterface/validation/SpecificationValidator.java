/**
 *
 * $Id$
 */
package adaptorinterface.validation;

import adaptorinterface.DomainSpecification;
import adaptorinterface.GenerationSetting;
import adaptorinterface.NamespacePrefix;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link adaptorinterface.Specification}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface SpecificationValidator {
	boolean validate();

	boolean validateDomainSpecifications(EList<DomainSpecification> value);
	boolean validateDomainPrefixes(EList<NamespacePrefix> value);
	boolean validateGenerationSetting(GenerationSetting value);
}