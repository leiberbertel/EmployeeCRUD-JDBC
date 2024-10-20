package org.leiber.repository.employee;

import org.leiber.config.ConfigDatabase;
import org.leiber.entity.EmployeeEntity;
import org.leiber.exception.GenericException;
import org.leiber.repository.Repository;
import org.leiber.utils.Constants.MagicNumber;
import org.leiber.utils.TextUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of repository for employees in charge of database operations. <br>
 * <p>
 * Created on 10/10/2024 at 11:30 p.m.
 *
 * @author Leiber Bertel
 */
public class EmployeeRepositoryImpl implements Repository<EmployeeEntity> {

    public static final Logger logger = Logger.getLogger(EmployeeRepositoryImpl.class.getName());

    /**
     * Creates an object of type employee with the properties obtained from ResultSet. <br>
     * <p>
     * Created on 10/10/2024 at 11:40 p.m.
     *
     * @param resultSet resultSet
     * @returns EmployeeEntity
     * @author Leiber Bertel
     */
    private static EmployeeEntity createEmployee(ResultSet resultSet) throws SQLException {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeId(resultSet.getInt("id"));
        employee.setFirstname(resultSet.getString("first_name"));
        employee.setFirstSurname(resultSet.getString("pa_surname"));
        employee.setSecondSurname(resultSet.getString("ma_surname"));
        employee.setEmail(resultSet.getString("email"));
        employee.setSalary(resultSet.getBigDecimal("salary"));
        employee.setCurp(resultSet.getString("curp"));
        return employee;
    }

    private Connection getConnection() throws SQLException {
        return ConfigDatabase.getConnection();
    }

    /**
     * Gets all employees <br>
     * Created on 10/10/2024 at 8:32 p.m.
     *
     * @return List<EmployeeEntity> employees
     * @author Leiber Bertel
     */
    @Override
    public List<EmployeeEntity> findAll() {
        String sql = "SELECT id, first_name, pa_surname, ma_surname, email, salary, curp FROM employees";
        List<EmployeeEntity> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                EmployeeEntity employee = createEmployee(resultSet);
                employees.add(employee);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while obtaining employee records.");
            throw new GenericException(e.getMessage());
        }
        return employees;
    }

    /**
     * Gets an employee for his or her id <br>
     * Created on 10/10/2024 at 8:32 p.m.
     *
     * @param id employee id
     * @return EmployeeEntity
     * @author Leiber Bertel
     */
    @Override
    public EmployeeEntity getById(Integer id) {
        EmployeeEntity employee = null;
        String sql = "SELECT id, first_name, pa_surname, ma_surname, email, salary, curp FROM employees WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(MagicNumber.ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = createEmployee(resultSet);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, String.format("Error when trying to get the employee with the id: %s", id));
            throw new GenericException(e.getMessage());
        }
        return employee;
    }


    /**
     * Saves and/or updates an employee.
     * Created on 10/10/2024 at 8:30 p.m.
     *
     * @param employeeEntity employee
     * @return EmployeeEntity
     * @author Leiber Bertel
     */
    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?, ?, ?, ?, ?, ?)";

        if (employeeEntity.getEmployeeId() != null) {
            updateEmployee(employeeEntity);
        } else {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(MagicNumber.ONE, employeeEntity.getFirstname());
                preparedStatement.setString(MagicNumber.TWO, employeeEntity.getFirstSurname());
                preparedStatement.setString(MagicNumber.THREE, employeeEntity.getSecondSurname());
                preparedStatement.setString(MagicNumber.FOUR, employeeEntity.getEmail());
                preparedStatement.setBigDecimal(MagicNumber.FIVE, employeeEntity.getSalary());
                preparedStatement.setString(MagicNumber.SIX, employeeEntity.getCurp());
                int rowAffected = preparedStatement.executeUpdate();

                if (rowAffected > MagicNumber.ZERO) {
                    logger.info("Registration successfully created");
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "An error occurred while registering the employee");
                throw new GenericException(e.getMessage());
            }
        }
        return employeeEntity;
    }

    /**
     * Conditionally updates an employee's properties.
     * Created on 10/10/2024 at 8:10 p.m.
     *
     * @param employeeEntity employee
     * @author Leiber Bertel
     */
    private void updateEmployee(EmployeeEntity employeeEntity) {
        String sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?,email = ?, salary = ?, curp = ? WHERE id = ?";

        EmployeeEntity existingEmployee = this.getById(employeeEntity.getEmployeeId());

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(MagicNumber.ONE, TextUtil.isEmptyNull(employeeEntity.getFirstname()) ? existingEmployee.getFirstname() : employeeEntity.getFirstname());
            preparedStatement.setString(MagicNumber.TWO, TextUtil.isEmptyNull(employeeEntity.getFirstSurname()) ? existingEmployee.getFirstSurname() : employeeEntity.getFirstSurname());
            preparedStatement.setString(MagicNumber.THREE, TextUtil.isEmptyNull(employeeEntity.getSecondSurname()) ? existingEmployee.getSecondSurname() : employeeEntity.getSecondSurname());
            preparedStatement.setString(MagicNumber.FOUR, TextUtil.isEmptyNull(employeeEntity.getEmail()) ? existingEmployee.getEmail() : employeeEntity.getEmail());
            preparedStatement.setBigDecimal(MagicNumber.FIVE, Objects.equals(employeeEntity.getSalary(), new BigDecimal(0)) ? existingEmployee.getSalary() : employeeEntity.getSalary());
            preparedStatement.setString(MagicNumber.SIX, TextUtil.isEmptyNull(employeeEntity.getCurp()) ? existingEmployee.getCurp() : employeeEntity.getCurp());
            preparedStatement.setInt(MagicNumber.SEVEN, employeeEntity.getEmployeeId());
            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > MagicNumber.ZERO) {
                logger.info("Registration has been successfully updated");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "It has occurred when updating the employee");
            throw new GenericException(e.getMessage());
        }
    }

    /**
     * Delete an employee by id. <br>
     * Created on 12/10/2024 at 6:01 p.m.
     *
     * @param id employeeId
     * @return true/false
     * @author Leiber Bertel
     */
    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id, "The id cannot be null");
        Boolean result = Boolean.FALSE;

        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(MagicNumber.ONE, id);
            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > MagicNumber.ZERO) {
                result = Boolean.TRUE;
                logger.info("Record was successfully deleted");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, String.format("Error when trying to delete employee with id: %s", id));
            throw new GenericException(e.getMessage());
        }
        return result;
    }
}
