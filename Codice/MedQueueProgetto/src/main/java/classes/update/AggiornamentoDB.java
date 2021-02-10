package classes.update;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneModel;

import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AggiornamentoDB implements ServletContextListener {
    private ScheduledExecutorService scheduler;
    static Logger logger = Logger.getLogger("global");

    public void contextInitialixed(ServletContextListener sce) {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        try {
            logger.info("Mi connetto al db...");
            Connection con = DriverManagerConnectionPool.getConnection();
            logger.info("Connessione stabilita");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                logger.info("Controllo di prenotazioni non convalidate");
                long mills = System.currentTimeMillis();
                Date now = new Date(mills);

                PrenotazioneModel pm = new PrenotazioneModel();
                try {
                    Collection<PrenotazioneBean> p = pm.doRetrieveAll("dataPrenotazione");

                    for (PrenotazioneBean app: p) {
                        if (app.getDataPrenotazione().before(now)) {
                            pm.doDelete(app);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.HOURS);
    }

    public void contextDestroyed(ServletContextListener sce) {
        scheduler.shutdown();
    }
}
