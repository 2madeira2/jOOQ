import generated.tables.records.NomenclatureRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.NOMENCLATURE;

public final class NomenclatureDAO implements DAO<Nomenclature> {
    final Connection connection;
    final DSLContext context;

    public NomenclatureDAO(Connection connection) {
        this.connection = connection;
        this.context = DSL.using(connection, SQLDialect.POSTGRES);
    }

    @Override
    public Nomenclature get(int id) {

        NomenclatureRecord record = context
                .selectFrom(NOMENCLATURE)
                .where(NOMENCLATURE.ID.eq(id))
                .fetchOne();

        if(record == null){
            throw new IllegalStateException("Nothing was found for your query");
        }
        else {
            return new Nomenclature(record.getId(), record.getName(), Math.toIntExact(record.getInternalCode()));
        }
    }

    @Override
    public List<Nomenclature> getAll() {

        final List<Nomenclature> all_list = new ArrayList<>();

        Result<NomenclatureRecord> records = context
                .selectFrom(NOMENCLATURE)
                .fetch();
        for (NomenclatureRecord record : records) {
            all_list.add(new Nomenclature(record.getId(), record.getName(), Math.toIntExact(record.getInternalCode())));
        }
        return all_list;
    }


    @Override
    public void save(Nomenclature entity) {

         context.insertInto(NOMENCLATURE, NOMENCLATURE.ID, NOMENCLATURE.NAME, NOMENCLATURE.INTERNAL_CODE)
                .values(entity.getId(), entity.getName(), (long) entity.getInternalCode())
                .execute();

    }


    @Override
    public void update(Nomenclature entity) {

         context.update(NOMENCLATURE)
                .set(NOMENCLATURE.NAME, entity.getName())
                .set(NOMENCLATURE.INTERNAL_CODE, (long) entity.getInternalCode())
                .where(NOMENCLATURE.ID.eq(entity.getId()))
                .execute();

    }

    @Override
    public void delete(Nomenclature entity) {
        context.delete(NOMENCLATURE)
                .where(NOMENCLATURE.ID.eq(entity.getId()))
                .execute();
    }
}
