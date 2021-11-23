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

        NomenclatureRecord record = context.fetchOne(NOMENCLATURE, NOMENCLATURE.ID.eq(id));

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

        Result<NomenclatureRecord> records = context.fetch(NOMENCLATURE);
        for (NomenclatureRecord record : records) {
            all_list.add(new Nomenclature(record.getId(), record.getName(), Math.toIntExact(record.getInternalCode())));
        }
        return all_list;
    }


    @Override
    public void save(Nomenclature entity) {

        final NomenclatureRecord record = context.newRecord(NOMENCLATURE);
        record.setId(entity.getId())
              .setName(entity.getName())
              .setInternalCode((long) entity.getInternalCode());
        record.store();

    }


    @Override
    public void update(Nomenclature entity) {
        final NomenclatureRecord record = context.fetchOne(NOMENCLATURE,NOMENCLATURE.ID.eq(entity.getId()));
        assert record != null;
        record.setName(entity.getName())
              .setInternalCode((long) entity.getInternalCode());
         record.store();
    }

    @Override
    public void delete(Nomenclature entity) {
        context.delete(NOMENCLATURE)
                .where(NOMENCLATURE.ID.eq(entity.getId()))
                .execute();
    }
}
