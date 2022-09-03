/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pdc_project2;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import pdc_project2.ui.MainWindow;
import pdc_project2.util.DatabaseInit;

/**
 *
 * @author Tai Zhang 17970814
 */
public class PDC_Project2 {

	public static void main(String[] args) {
		exportDatabase();

		new MainWindow();
	}

	public static void exportDatabase() {
		/**
		 * Read Hibernate XML File Initialize Database
		 */
		Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);

		try {
			DatabaseInit.initData();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		// HibernateUtils.shutdown();
	}
}