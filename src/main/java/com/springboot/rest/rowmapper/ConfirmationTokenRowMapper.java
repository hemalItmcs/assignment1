package com.springboot.rest.rowmapper;

import com.springboot.rest.customexception.CommonException;
import java.math.BigInteger;
import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import com.springboot.rest.entity.ConfirmationTokenModel;
import com.springboot.rest.utility.UtilityService;
import org.apache.logging.log4j.LogManager;

/**
 * SpringBootRestDemo 2019 Filename: ConfirmationTokenRowMapper.java Description: ConfirmationTokenRowMapper class
 * request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
public class ConfirmationTokenRowMapper implements RowMapper<ConfirmationTokenModel>{
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ConfirmationTokenRowMapper.class);
	
	/**
	 * Method is used for map database result set to the model file.
	 *
	 * @author Itmusketeers
	 * @version 1.0
	 * @Last modified 2019-03-07
	 */
	@Override
	public ConfirmationTokenModel mapRow(ResultSet row, int rowNum) {
		
            try {
                ConfirmationTokenModel confirmationTokenModel = new ConfirmationTokenModel();
                confirmationTokenModel.setTokenid(new BigInteger(row.getString("confirmationtokenPk")));
                confirmationTokenModel.setConfirmationToken(row.getString("confirmationtoken"));
                confirmationTokenModel.setCreatedDate(UtilityService.convertStringTODate(row.getString("createdDate")));
                confirmationTokenModel.setUser(new BigInteger(row.getString("userid")));
                return confirmationTokenModel;
            } catch (Exception ex) {
                LOGGER.error(ex);
                try {
                    throw new CommonException(ex.getMessage());
                } catch (CommonException ex1) {
                    LOGGER.error(ex1);
                }
                return null;
            }
            
	}

}
