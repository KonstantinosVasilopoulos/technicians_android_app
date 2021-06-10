package gr.aueb.team14.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.CustomerDAO;
import gr.aueb.team14.domain.Customer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Tests the activity which handles the customer's registration.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CustomerRegistrationTest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String tk;

    @Rule
    public ActivityScenarioRule<CustomerRegistration> activityRule =
            new ActivityScenarioRule<>(CustomerRegistration.class);

    @Before
    public void setUp() throws Exception {
        username = "Johnny";
        password = "testing321";
        email = "johnny@example.com";
        phone = "6900000000";
        address = "Acropolis 31";
        tk = "56789";
    }

    /**
     * Attempts to create a new customer with the previous randomly chosen data.
     * The user has been created successfully if the CustomerDAO is able to return a customer with
     * the same email as the one given.
     */
    @Test
    public void registerJohnny() {
        // Type the new user's information
        onView(withId(R.id.customerUsernameField)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.customerEmailField)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.customerPhoneField)).perform(typeText(phone), closeSoftKeyboard());
        onView(withId(R.id.customerAddressField)).perform(typeText(address), closeSoftKeyboard());
        onView(withId(R.id.customertkField)).perform(typeText(tk), closeSoftKeyboard());
        onView(withId(R.id.customerPassword1Field)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.customerPassword2Field)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.customerRegistrationButton)).perform(click());

        Customer customer = CustomerDAO.getInstance().find(username);
        assertEquals(customer.getEmail(), email);
    }
}