package br.jsec2.domain;

public interface Sequenceable {

	public default String getSequenceName() {
		return String.format("SEQ_%s", this.getClass().getSimpleName().toUpperCase());
	}

	public default String getNextValSQL() {
		return String.format("SELECT %s.NEXTVAL FROM DUAL", this.getSequenceName());
	}
}
