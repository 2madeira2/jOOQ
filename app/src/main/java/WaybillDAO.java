import generated.tables.records.WaybillRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.WAYBILL;

public class WaybillDAO implements DAO<Waybill> {
    final Connection connection;
    final DSLContext context;

    public WaybillDAO(Connection connection) {
        this.connection = connection;
        this.context = DSL.using(connection, SQLDialect.POSTGRES);
    }

    public Waybill get(int waybill_num) {
        WaybillRecord record = context.selectFrom(WAYBILL)
                .where(WAYBILL.WAYBILL_NUM.eq(waybill_num)).fetchOne();

        if(record == null){
            throw new IllegalStateException("No results for this request");
        }
        else{
            return new Waybill(record.getWaybillNum(), record.getWaybillDate(), record.getOrgSender());
        }
    }

    @Override
    public List<Waybill> getAll() {
        final List<Waybill> all_list = new ArrayList<>();
        Result<WaybillRecord> records = context
                .selectFrom(WAYBILL)
                .fetch();

        for (WaybillRecord record : records) {
            all_list.add(new Waybill(record.getWaybillNum(), record.getWaybillDate(), record.getOrgSender()));
        }
        return all_list;
    }

    @Override
    public void save(Waybill entity) {
         context.insertInto(WAYBILL, WAYBILL.WAYBILL_NUM, WAYBILL.WAYBILL_DATE, WAYBILL.ORG_SENDER)
                .values(entity.getWaybillNum(), entity.getWaybillDate(), entity.getOrgSender())
                .execute();
    }


    @Override
    public void update(Waybill entity) {
         context.update(WAYBILL)
                .set(WAYBILL.WAYBILL_DATE, entity.getWaybillDate())
                .set(WAYBILL.ORG_SENDER, entity.getOrgSender())
                .where(WAYBILL.WAYBILL_NUM.eq(entity.getWaybillNum()))
                .execute();
    }

    @Override
    public void delete(Waybill entity) {
         context.delete(WAYBILL)
                .where(WAYBILL.WAYBILL_NUM.eq(entity.getWaybillNum()))
                .execute();
    }
}
