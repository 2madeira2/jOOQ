import org.flywaydb.core.Flyway;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static generated.Tables.*;
import static org.jooq.impl.DSL.avg;
import static org.jooq.impl.DSL.sum;

public class Main {
    public static void main(String[] args) {


        final String second_request = "select w.org_sender from waybill w where w.waybill_num in (select wp.waybill from waybill_position wp where wp.amount > ?);";
        final String third_request = "select sum(wp.amount)as total_amount, sum(wp.price) as total_price from waybill_position wp where wp.waybill in( select w.waybill_num from waybill w where w.waybill_date between ? and ?);";
        final String fourth_request = "select avg(wp.price) from waybill_position wp where wp.waybill in( select w.waybill_num from waybill w where w.waybill_date between ? and ?);";


        final Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "whoami")
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();



        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "whoami")) {

            System.out.println("Connection OK.");

            OrganizationDAO organizationDAO = new OrganizationDAO(connection);
            organizationDAO.save(new Organization("Magnit", 34, 432432451));
            organizationDAO.save(new Organization("Patyorochka",344314,43244451));
            organizationDAO.save(new Organization("Centre",34214314,432432241));
            organizationDAO.save(new Organization("Perekrestok",342143134,43244342));
            organizationDAO.save(new Organization("Xiaomi",33144242,42243251));
            organizationDAO.save(new Organization("adidas",34314,434245142));
            organizationDAO.save(new Organization("FixPrice",7414,434245143));
            organizationDAO.save(new Organization("Nike",2177314,434242421));
            organizationDAO.save(new Organization("Puma",344344314,422254241));
            organizationDAO.save(new Organization("Billa",34214,42411));
            organizationDAO.save(new Organization("Mail",34217744,434222));
            organizationDAO.save(new Organization("Yandex",342175314,434224341));


            WaybillDAO waybillDAO = new WaybillDAO(connection);
            waybillDAO.save(new Waybill(1, LocalDate.of(2010, 4, 1),"Magnit"));
            waybillDAO.save(new Waybill(2, LocalDate.of(2010, 5, 1),"Patyorochka"));
            waybillDAO.save(new Waybill(3, LocalDate.of(2010, 6, 1),"Perekrestok"));
            waybillDAO.save(new Waybill(4, LocalDate.of(2010, 7, 1),"Centre"));
            waybillDAO.save(new Waybill(5, LocalDate.of(2010, 8, 1),"Xiaomi"));
            waybillDAO.save(new Waybill(6, LocalDate.of(2010, 9, 1),"adidas"));
            waybillDAO.save(new Waybill(7, LocalDate.of(2010, 10, 1),"FixPrice"));
            waybillDAO.save(new Waybill(8, LocalDate.of(2010, 11, 1),"Nike"));
            waybillDAO.save(new Waybill(9, LocalDate.of(2010, 12, 1),"Puma"));
            waybillDAO.save(new Waybill(10, LocalDate.of(2011, 1, 1),"Billa"));
            waybillDAO.save(new Waybill(11, LocalDate.of(2011, 2, 1),"Mail"));
            waybillDAO.save(new Waybill(12, LocalDate.of(2011, 3, 1),"Yandex"));

            //Insert into Nomenclature.
            NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(connection);
            nomenclatureDAO.save(new Nomenclature(1, "Milk", 2242));
            nomenclatureDAO.save(new Nomenclature(2, "Sneaker", 24432));
            nomenclatureDAO.save(new Nomenclature(3, "Smart_Station", 3224));


            //Into WayBill_position.

            WaybillPositionDAO waybillPositionDAO = new WaybillPositionDAO(connection);
            waybillPositionDAO.save(new WaybillPosition(1,540000,1,40000,1));
            waybillPositionDAO.save(new WaybillPosition(2,550000,1,41000,2));
            waybillPositionDAO.save(new WaybillPosition(3,530000,1,44000,3));
            waybillPositionDAO.save(new WaybillPosition(4,540000,1,42000,4));
            waybillPositionDAO.save(new WaybillPosition(5,520000,1,44000,7));
            waybillPositionDAO.save(new WaybillPosition(6,560000,1,40000,10));
            waybillPositionDAO.save(new WaybillPosition(7,1000000,2,5200,6));
            waybillPositionDAO.save(new WaybillPosition(8,1200000,2,5100,8));
            waybillPositionDAO.save(new WaybillPosition(9,1400000,2,5400,9));
            waybillPositionDAO.save(new WaybillPosition(10,500000,3,11000,5));
            waybillPositionDAO.save(new WaybillPosition(11,510000,3,13200,12));
            waybillPositionDAO.save(new WaybillPosition(12,490000,3,13100,11));


            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);


            System.out.println("Report 1: Top 10 suppliers of the number of delivered goods ");
            Result<Record1<String>> result1 = context.select(WAYBILL.ORG_SENDER)
                    .from(WAYBILL)
                    .join(WAYBILL_POSITION).on(WAYBILL.WAYBILL_NUM.eq(WAYBILL_POSITION.WAYBILL.cast(Integer.TYPE)))
                    .groupBy(WAYBILL.ORG_SENDER)
                    .orderBy(sum(WAYBILL_POSITION.AMOUNT).desc())
                    .limit(10)
                    .fetch();

            for (Record record : result1) {
                System.out.println(record.getValue(WAYBILL.ORG_SENDER));
            }



           System.out.println("Report 2: suppliers of the number of delivered goods greater than 42000");

            Result<Record1<String>> result2 = context.select(WAYBILL.ORG_SENDER)
                    .from(WAYBILL)
                    .where(WAYBILL.WAYBILL_NUM.in((context.select(WAYBILL_POSITION.WAYBILL.cast(Integer.TYPE))
                            .from(WAYBILL_POSITION)
                            .where(WAYBILL_POSITION.AMOUNT.gt((long) 42000)))))
                    .fetch();

            for (Record record : result2) {
                System.out.println(record);
            }


            System.out.println("Report 3: quantity and amount of goods received in the specified period ");

            Result<Record2<BigDecimal, BigDecimal>> result3 = context.select(sum(WAYBILL_POSITION.AMOUNT).as("total_amount"), sum(WAYBILL_POSITION.PRICE).as("total_price"))
                            .from(WAYBILL_POSITION)
                            .where(WAYBILL_POSITION.WAYBILL.in((context.select(WAYBILL.WAYBILL_NUM.cast(Long.TYPE))
                            .from(WAYBILL)
                            .where(WAYBILL.WAYBILL_DATE.between(LocalDate.of(2010, 4, 1)).and(LocalDate.of(2010,7,1))))))
                            .fetch();

            for (Record record : result3) {
                System.out.println(record);
            }



            System.out.println("Report 4: average price of goods for the received period ");

            Result<Record2<String,BigDecimal>> result4 = context.select(NOMENCLATURE.NAME, avg(WAYBILL_POSITION.PRICE))
                            .from(NOMENCLATURE)
                            .join(WAYBILL_POSITION).on(NOMENCLATURE.ID.eq(WAYBILL_POSITION.NOMENCLATURE.cast(Integer.TYPE)))
                            .join(WAYBILL).on(WAYBILL_POSITION.WAYBILL.eq(WAYBILL.WAYBILL_NUM.cast(Long.TYPE)))
                            .where(WAYBILL.WAYBILL_DATE.between(LocalDate.of(2010,4,1)).and(LocalDate.of(2010, 7, 1)))
                            .groupBy(NOMENCLATURE.NAME)
                            .fetch();


            for (Record record : result4) {
                System.out.println(record);
            }


            System.out.println("Report 5: list of goods supplied by organizations for the period ");

            Result<Record2<String, String>> result5 = context.select(ORGANIZATION.NAME.as("Organization"), NOMENCLATURE.NAME.as("Product"))
                    .from(ORGANIZATION)
                    .leftJoin(WAYBILL).on(ORGANIZATION.NAME.eq(WAYBILL.ORG_SENDER).and(WAYBILL.WAYBILL_DATE
                            .between(LocalDate.of(2010, 4, 1)).and(LocalDate.of(2011, 3, 1))))
                    .leftJoin(WAYBILL_POSITION).on(WAYBILL.WAYBILL_NUM.eq(WAYBILL_POSITION.WAYBILL.cast(Integer.TYPE)))
                    .leftJoin(NOMENCLATURE).on(WAYBILL_POSITION.NOMENCLATURE.eq(NOMENCLATURE.ID.cast(Long.TYPE)))
                    .groupBy(ORGANIZATION.NAME, NOMENCLATURE.ID)
                    .orderBy(ORGANIZATION.NAME)
                    .fetch();
            for (Record record : result5) {
                System.out.println(record);
            }

       } catch (SQLException throwables) {
          System.out.println("Connection failure.");
          throwables.printStackTrace();
      }
    }
}
