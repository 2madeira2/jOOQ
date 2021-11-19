/*
 * This file is generated by jOOQ.
 */
package generated.tables.records;


import generated.tables.Organization;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganizationRecord extends UpdatableRecordImpl<OrganizationRecord> implements Record3<String, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.organization.name</code>.
     */
    public OrganizationRecord setName(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.organization.name</code>.
     */
    public String getName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.organization.INN</code>.
     */
    public OrganizationRecord setInn(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.organization.INN</code>.
     */
    public Long getInn() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.organization.checking_account</code>.
     */
    public OrganizationRecord setCheckingAccount(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.organization.checking_account</code>.
     */
    public Long getCheckingAccount() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, Long, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Organization.ORGANIZATION.NAME;
    }

    @Override
    public Field<Long> field2() {
        return Organization.ORGANIZATION.INN;
    }

    @Override
    public Field<Long> field3() {
        return Organization.ORGANIZATION.CHECKING_ACCOUNT;
    }

    @Override
    public String component1() {
        return getName();
    }

    @Override
    public Long component2() {
        return getInn();
    }

    @Override
    public Long component3() {
        return getCheckingAccount();
    }

    @Override
    public String value1() {
        return getName();
    }

    @Override
    public Long value2() {
        return getInn();
    }

    @Override
    public Long value3() {
        return getCheckingAccount();
    }

    @Override
    public OrganizationRecord value1(String value) {
        setName(value);
        return this;
    }

    @Override
    public OrganizationRecord value2(Long value) {
        setInn(value);
        return this;
    }

    @Override
    public OrganizationRecord value3(Long value) {
        setCheckingAccount(value);
        return this;
    }

    @Override
    public OrganizationRecord values(String value1, Long value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrganizationRecord
     */
    public OrganizationRecord() {
        super(Organization.ORGANIZATION);
    }

    /**
     * Create a detached, initialised OrganizationRecord
     */
    public OrganizationRecord(String name, Long inn, Long checkingAccount) {
        super(Organization.ORGANIZATION);

        setName(name);
        setInn(inn);
        setCheckingAccount(checkingAccount);
    }
}