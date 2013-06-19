package husacct.validate.domain.validation.violationtype;

import husacct.validate.domain.validation.DefaultSeverities;

public enum ViolationTypes implements IViolationType {
	CALL("Call", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	ACCESS("Access", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	IMPORT("Import", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.LOW),
	DECLARATION("Declaration", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	INHERITANCE("Inheritance", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	ANNOTATION("Annotation", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.LOW),
	
	//TODO Delete if unneccesary (after introduction 6 core dependency types)
	ACCESS_PROPERTY_OR_FIELD("AccessPropertyOrField", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	EXCEPTION("Exception", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	EXTENDS_CONCRETE("ExtendsConcrete", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	EXTENDS_ABSTRACT("ExtendsAbstract", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	EXTENDS_LIBRARY("ExtendsLibrary", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.LOW),
	EXTENDS_INTERFACE("ExtendsInterface", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.LOW),
	IMPLEMENTS("Implements", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	INVOC_CONSTRUCTOR("InvocConstructor", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	INVOC_METHOD("InvocMethod", ViolationCategories.DEPENDENCY_RECOGNITION, DefaultSeverities.MEDIUM),
	//END
	
	PUBLIC("public", ViolationCategories.ACCESS_MODIFIERS, DefaultSeverities.MEDIUM),
	PROTECTED("protected", ViolationCategories.ACCESS_MODIFIERS, DefaultSeverities.MEDIUM),
	DEFAULT("default", ViolationCategories.ACCESS_MODIFIERS, DefaultSeverities.LOW),
	PRIVATE("private", ViolationCategories.ACCESS_MODIFIERS, DefaultSeverities.LOW),
	PACKAGE("package", ViolationCategories.PACKAGING, DefaultSeverities.LOW),
	CLASS("class", ViolationCategories.PACKAGING, DefaultSeverities.LOW);
	
	private final String key;
	private final DefaultSeverities defaultSeverity;
	private final ViolationCategories violationCategory;

	ViolationTypes(String value, ViolationCategories violationCategory, DefaultSeverities defaultSeverity) {
		this.key = value;
		this.defaultSeverity = defaultSeverity;
		this.violationCategory = violationCategory;
	}

	@Override
	public String toString() {
		return key;
	}

	@Override
	public String getCategory() {
		return violationCategory.toString();
	}

	@Override
	public DefaultSeverities getDefaultSeverity() {
		return defaultSeverity;
	}
}
