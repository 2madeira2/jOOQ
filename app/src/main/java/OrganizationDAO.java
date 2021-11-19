import generated.tables.records.OrganizationRecord;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.*;
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

        OrganizationRecord record = context.selectFrom(ORGANIZATION)
                .where(ORGANIZATION.NAME.eq(name)).fetchOne();
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

        Result<OrganizationRecord> records = context
                .selectFrom(ORGANIZATION)
                .fetch();

        for (OrganizationRecord record : records) {
            all_list.add(new Organization(record.getName(), Math.toIntExact(record.getInn()), Math.toIntExact(record.getCheckingAccount())));
        }
        return all_list;
    }

    @Override
    public void save(Organization entity) {

         context.insertInto(ORGANIZATION, ORGANIZATION.NAME, ORGANIZATION.INN, ORGANIZATION.CHECKING_ACCOUNT)
                .values(entity.getName(), (long) entity.getINN(), (long) entity.getCheckingAccount())
                .execute();
    }


    @Override
    public void update(Organization entity) {
         context.update(ORGANIZATION)
                .set(ORGANIZATION.INN, (long) entity.getINN())
                .set(ORGANIZATION.CHECKING_ACCOUNT, (long) entity.getCheckingAccount())
                .where(ORGANIZATION.NAME.eq(entity.getName()))
                .execute();
    }

    @Override
    public void delete(Organization entity) {
         context.delete(ORGANIZATION)
                .where(ORGANIZATION.NAME.eq(entity.getName()))
                .execute();
    }
}
