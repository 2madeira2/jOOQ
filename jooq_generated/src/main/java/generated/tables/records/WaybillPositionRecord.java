/*
 * This file is generated by jOOQ.
 */
package generated.tables.records;


import generated.tables.WaybillPosition;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WaybillPositionRecord extends UpdatableRecordImpl<WaybillPositionRecord> implements Record5<Integer, Long, Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.waybill_position.position</code>.
     */
    public WaybillPositionRecord setPosition(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill_position.position</code>.
     */
    public Integer getPosition() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.waybill_position.price</code>.
     */
    public WaybillPositionRecord setPrice(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill_position.price</code>.
     */
    public Long getPrice() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.waybill_position.nomenclature</code>.
     */
    public WaybillPositionRecord setNomenclature(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill_position.nomenclature</code>.
     */
    public Long getNomenclature() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.waybill_position.amount</code>.
     */
    public WaybillPositionRecord setAmount(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill_position.amount</code>.
     */
    public Long getAmount() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.waybill_position.waybill</code>.
     */
    public WaybillPositionRecord setWaybill(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.waybill_position.waybill</code>.
     */
    public Long getWaybill() {
        return (Long) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Long, Long, Long, Long> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, Long, Long, Long, Long> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return WaybillPosition.WAYBILL_POSITION.POSITION;
    }

    @Override
    public Field<Long> field2() {
        return WaybillPosition.WAYBILL_POSITION.PRICE;
    }

    @Override
    public Field<Long> field3() {
        return WaybillPosition.WAYBILL_POSITION.NOMENCLATURE;
    }

    @Override
    public Field<Long> field4() {
        return WaybillPosition.WAYBILL_POSITION.AMOUNT;
    }

    @Override
    public Field<Long> field5() {
        return WaybillPosition.WAYBILL_POSITION.WAYBILL;
    }

    @Override
    public Integer component1() {
        return getPosition();
    }

    @Override
    public Long component2() {
        return getPrice();
    }

    @Override
    public Long component3() {
        return getNomenclature();
    }

    @Override
    public Long component4() {
        return getAmount();
    }

    @Override
    public Long component5() {
        return getWaybill();
    }

    @Override
    public Integer value1() {
        return getPosition();
    }

    @Override
    public Long value2() {
        return getPrice();
    }

    @Override
    public Long value3() {
        return getNomenclature();
    }

    @Override
    public Long value4() {
        return getAmount();
    }

    @Override
    public Long value5() {
        return getWaybill();
    }

    @Override
    public WaybillPositionRecord value1(Integer value) {
        setPosition(value);
        return this;
    }

    @Override
    public WaybillPositionRecord value2(Long value) {
        setPrice(value);
        return this;
    }

    @Override
    public WaybillPositionRecord value3(Long value) {
        setNomenclature(value);
        return this;
    }

    @Override
    public WaybillPositionRecord value4(Long value) {
        setAmount(value);
        return this;
    }

    @Override
    public WaybillPositionRecord value5(Long value) {
        setWaybill(value);
        return this;
    }

    @Override
    public WaybillPositionRecord values(Integer value1, Long value2, Long value3, Long value4, Long value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WaybillPositionRecord
     */
    public WaybillPositionRecord() {
        super(WaybillPosition.WAYBILL_POSITION);
    }

    /**
     * Create a detached, initialised WaybillPositionRecord
     */
    public WaybillPositionRecord(Integer position, Long price, Long nomenclature, Long amount, Long waybill) {
        super(WaybillPosition.WAYBILL_POSITION);

        setPosition(position);
        setPrice(price);
        setNomenclature(nomenclature);
        setAmount(amount);
        setWaybill(waybill);
    }
}
