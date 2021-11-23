import generated.tables.records.OrganizationRecord;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.ORGANIZATION;

public final class OrganizationDAO implements DAO<Organization> {

    final Connection connection;
    final DSLContext context;

    public OrganizationDAO(Connection connection) {
        this.connection = connection;
        this.context = DSL.using(connection, SQLDialect.POSTGRES);
    }

    @Override
    public Organization get(int id) {
        return null;
    }

    public Organization get(@NotNull String name) {

        OrganizationRecord record = context.fetchOne(ORGANIZATION, ORGANIZATION.NAME.eq(name));
        if(record == null){
            throw new IllegalStateException("No results for your request");
        }
        else{
            return new Organization(record.getName(),
                                    Math.toIntExact(record.getInn()),
                                    Math.toIntExact(record.getCheckingAccount()));
        }
    }

    @Override
    public List<Organization> getAll() {
        final List<Organization> all_list = new ArrayList<>();

        Result<OrganizationRecord> records = context.fetch(ORGANIZATION);

        for (OrganizationRecord record : records) {
            all_list.add(new Organization(record.getName(), Math.toIntExact(record.getInn()), Math.toIntExact(record.getCheckingAccount())));
        }
        return all_list;
    }

    @Override
    public void save(Organization entity) {

        final OrganizationRecord record = context.newRecord(ORGANIZATION);
        record.setName(entity.getName())
                .setInn((long) entity.getINN())
                .setCheckingAccount((long) entity.getCheckingAccount());
        record.store();

    }


    @Override
    public void update(Organization entity) {
        OrganizationRecord record = context.fetchOne(ORGANIZATION, ORGANIZATION.NAME.eq(entity.getName()));
        record.setInn((long) entity.getINN())
              .setCheckingAccount((long) entity.getCheckingAccount())
              .setName(entity.getName());
        record.store();
    }

    @Override
    public void delete(Organization entity) {
        OrganizationRecord record = context.fetchOne(ORGANIZATION, ORGANIZATION.NAME.eq(entity.getName()));
        record.delete();
    }
}
