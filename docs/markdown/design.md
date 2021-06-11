# Περιγραφή κλάσεων του συστήματος

## Διάγραμμα κλάσεων της λογικής του πεδίου

![Διάγραμμα κλάσεων](uml/class_diagrams/class_diagram.png)

## User

### Περιγραφή κλάσης

Το interface *User* αποτελεί το αρχέτυπο για τον *Customer* και τον *Technician*. Μέσω της *User* υλοποιούνται οι λειτουργίες ασφαλής αποθήκευσης κωδικών με χρήση του αλγορίθμου SHA-1 και η εγγύηση πως οι δωσμένοι κωδικοί κατά την εγγραφή ενός χρήστη ταιριάζουν

### Διάγραμμα κλάσης

![Διάγραμμα κλάσης User](uml/class_diagrams/user_class_diagram.png)

## Appointment

### Περιγραφή κλάσης

Η κλάση *Appointment* συμβολίζει ένα ραντεβού μεταξύ ενός *πελάτη* και ενός *τεχνικού*. Η κλάση σχετίζεται με τις κλάσεις *Payment*, *Customer* και *Job*. Ιδιατερότητα της εν λόγω κλάσης αποτελεί το γεγονός ότι η κλάση δημιουργεί και συσχετίζεται με μία οντότητα της *Payment*. O constructor της κλάσης δίνεται παρακάτω.

```
public Appointment(Date from, Date to,double amount) {
    this.from = from;
    this.to = to;
    this.completed = false;
    this.confirmed = false;
    setPayment(new Payment(amount));
    this.jobs = new ArrayList<>();
}
```

### Διάγραμμα κλάσης

![Διάγραμμα κλάσης Appointment](uml/class_diagrams/appointment_class_diagram.png)

### Υλοποίηση των συσχετίσεων

#### Payment

Η *Payment* με την *Appointment* διατηρούν μία σχέση ένα προς ένα. Οπότε, η *Appointment* κρατάει μία οντότητα της *Payment* και έχει τους ακόλουθους getter και setter.

```
public Payment getPayment() {
    return payment;
}

public void setPayment(Payment payment) {
    this.payment = payment;
    this.payment.setAppointment(this);
}
```

#### Customer

Η κλάση *Appointment* διαθέτει ένα instance της *Customer*.

#### Job

Η *Appointment* και η *Job* συνδέονται με σχέση πολλά προς πολλά. Για αυτό το λόγο η *Appointment* διατηρεί μία λίστα με οντότητες τύπου *Job*. Η λίστα ανανεώνεται με τις ακόλουθες μεθόδους.

```
public void addJob(Job job) {
    if(!jobs.contains(job)){
        jobs.add(job);
        job.addAppointment(this);
    }
}

public void removeJob(Job job) {
    if(jobs.contains(job)){
        jobs.remove(job);
        job.removeAppointment(this);
    }
}
```

## AvailableDate

### Περιγραφή κλάσης

Η κλάση *AvailableDate* συμβολίζει μία ημερομηνία στην οποία ο *Technician* είναι διαθέσιμος. Η μεταβλητή booked της κλάσης δηλώνει εάν ο *τεχνικός* έχει ραντεβού σε αυτή την ημερομηνία.

### Διάγραμμα κλάσης

![Διάγραμμα κλάσης AvailableDate](uml/class_diagrams/available_date_class_diagram.png)

## Customer

### Περιγραφή κλάσης

H *Customer* αναπαριστά έναν *πελάτη* της εφαρμογής και επεκτείνει την κλάση *User*. Αναγνωριστικό ενός *πελάτη* αποτελεί το email του, το οποίο αποθηκεύεται στην κλάση *Customer*.

### Διάγραμμα κλάσης

![Διάγραμμα κλάσης Customer](uml/class_diagrams/customer_class_diagram.png)

### Υλοποίηση των συσχετίσεων

#### Appointment

Η *Customer* συνδέεται με την *Appointment* μέσω μίας one-to-many συσχέτισης. Αυτό σημαίνει πως ο *Customer* είναι υποχρεωμένος να κρατάει οντότητες τύπου *Appointment* σε μία λίστα. Τα περιεχόμενα της λίστας με τα *ραντεβού* διατρέχονται και μεταβάλλονται με τις παρακάτω μεθόδους. Ιδιατερότητα της υλοποίησης του πελάτη αποτελεί η μέθοδος `boolean checkPasswords(String, String)`, η οποία συγκρίνει δύο κωδικούς και επιστρέφει αληθής ή ψευδής ανάλογα με το αποτέλεσμα.

```
public List<Appointment> getAppointments() {
    return appointments;
}

public void addAppointment(Appointment appointment) {
    if(!appointments.contains(appointment)){
        appointments.add(appointment);
        appointment.setCustomer(this);
    }
}

public void removeAppointment(Appointment appointment) {
    if (appointments.contains(appointment)) {
        appointments.remove(appointment);
        appointment.setCustomer(null);
    }
}
```

## Job

### Περιγραφή κλάσης

Η κλάση *Job* υλοποιεί τις διαθέσιμες δουλειές που μπορεί να εκτελέσει ένας *τενικός*. Κάθε δουλειά χρειάζεται ένα όνομα(πχ "Wall painting" ή "Pipe changing") και μία τιμή.

### Διάγραμμα κλάσης

![Διάγραμμα κλάσης Job](uml/class_diagrams/job_class_diagram.png)

### Υλοποίηση των συσχετίσεων

#### Technician

Η *Job* διατηρεί μία οντότητα τύπου *Technician*.

#### Appointment

Η *Job* υλοποιεί την δικιά της πλευρά της many-to-many συσχέτισης με την *Appointment*.

```
public List<Appointment> getAppointments() {
    return appointments;
}

public void addAppointment(Appointment appointment) {
    if(!appointments.contains(appointment)){
        appointments.add(appointment);
        appointment.addJob(this);
    }
}

public void removeAppointment(Appointment appointment) {
    if (appointments.contains(appointment)) {
        appointments.remove(appointment);
        appointment.removeJob(this);
    }
}
```

## Payment

### Περιγραφή κλάσης

Η κλάση *Payment* συμβολίζει μία πληρωμή ενός *πελάτη* για ένα *ραντεβού*.

### Διάγραμμα κλάσης

![Διάγραμμα κλάσης Payment](uml/class_diagrams/payment_class_diagram.png)

### Υλοποίηση των συσχετίσεων

#### Appointment

Η *Payment* και η *Appointment* σχετίζονται με μία ένα προς ένα σύνδεση.

```
public Appointment getAppointment() {
    return appointment;
}

public void setAppointment(Appointment appointment) {
    this.appointment = appointment;
}
```

## Technician

### Περιγραφή κλάσης

Ένα *τεχνικός* αναπαριστάται από την κλάση *Technician*. Τα στοιχεία επικοινωνίας του *τεχνικού* δίνονται από τις μεταβλητές `communicationType` και `communicationValue`. Η πρώτη μεταβλητή αποθηκεύει τον τύπο επικοινωνίας που έχει επιλέξει ο *τεχνικός*(Email, SMS ή Phone) και η δεύτερη την τιμή του τύπου επικοινωνίας(πχ test@example.com).

### Διάγραμμα κλάσης

![Διάγραμμα κλάσης Technician](uml/class_diagrams/technician_class_diagram.png)

### Υλοποίηση των συσχετίσεων

#### AvailableDate

Η κλάση διαθέτει λίστες με τις ημερομηνίες που ο *τεχνικός* είναι διαθέσιμος.

```
public List<AvailableDate> getAvailableDates() {
    return availableDates;
}

public void addAvailableDate(AvailableDate date){
    if(!availableDates.contains(date)){
        availableDates.add(date);
        date.setTechnician(this);
    }
}
public void removeAvailableDate(AvailableDate date){
    if(availableDates.contains(date)){
        availableDates.remove(date);
        date.setTechnician(null);
    }
}
```

#### Specialty

Οι ειδικότητες του *τεχνικού* αποθηκεύονται σε μία λίστα από *Specialty*.

```
public void setSpecialties(List<Specialty> specialties) {
    this.specialties = specialties;
}

public List<Specialty> getSpecialties() {
    return specialties;
}

public void addSpecialty(Specialty specialty) {
    if (!specialties.contains(specialty))
        specialties.add(specialty);
}

public void removeSpecialty(List<Specialty> special) {
    specialties.remove(special);
}
```

#### Job

Οι *δουλειές* που μπορεί να κάνει ένας τεχνικός διατηρούνται σε μία λίστα απο οντότητες τύπου *Job*.

```
public List<Job> getJobs() {
    return jobs;
}

public void addJob(Job job) {
    if(!jobs.contains(job)){
        jobs.add(job);
        job.setTechnician(this);
    }
}

public void removeJob(Job job) {
    if(jobs.contains(job)){
        jobs.remove(job);
        job.setTechnician(null);
    }
}
```

## Υλοποίηση των controllers του συστήματος μέ κλάσεις DAO

### AppointmentDAO

![Διάγραμμα κλάσης](uml/class_diagrams/appointmentdao_class_diagram.png)

### CustomerDAO

![Διάγραμμα κλάσης](uml/class_diagrams/customerdao_class_diagram.png)

### TechnicianDAO

![Διάγραμμα κλάσης](uml/class_diagrams/techniciandao_class_diagram.png)

## Υλοποίηση διαγραμμάτων από τις κλάσεις του μοντέλου πεδίου

### [ΠΧ 1 - Εγγραφή Πελάτη](uc1-client-registration.md)

#### Διαγράμματα

![Διάγραμμα δραστηριοτήτων](uml/requirements/uc1-activity-diagram.png)

![Διάγραμμα ακολουθίας - Εγγραφή Πελάτη](uml/requirements/uc1-sequence-diagram.png)

### [ΠΧ 2 - Εγγραφή Τεχνικού](uc2-technician-registration.md)

#### Διαγράμματα

![Διάγραμμα δραστηριοτήτων](uml/requirements/uc2-activity-diagram.png)

![Διάγραμμα ακολουθίας - Εγγραφή Τεχνικού](uml/requirements/uc2-sequence-diagram.png)


### ΠΧ 3 - Προβολή Τεχνικών

#### Διάγραμμα

![Διάγραμμα ακολουθίας - Προβολή τεχνικών](uml/requirements/uc3-sequence-diagram.png)

### [ΠΧ 4 - Επιλογή Ραντεβού](uc4-appointment-selection.md)

#### Διαγράμματα

![Διάγραμμα δραστηριοτήτων](uml/requirements/uc4-activity-diagram.png)

![Διάγραμμα ακολουθίας - Επιλογή Ραντεβού](uml/requirements/uc4-sequence-diagram.png)

### [ΠΧ 5 - Επιβεβαίωση Ραντεβού](uc5-appointment-confirmation.md)

#### Διαγράμματα

![Διάγραμμα δραστηριοτήτων](uml/requirements/uc5-activity-diagram.png)

![Διάγραμμα ακολουθίας - Επιβεβαίωση Ραντεβού](uml/requirements/uc5-sequence-diagram.png)

### ΠΧ 6 - Ολοκλήρωση Ραντεβού

#### Διάγραμμα

![Διάγραμμα ακολουθίας - Ολοκλήρωση Ραντεβού](uml/requirements/uc6-sequence-diagram.png)

### ΠΧ 7 - Εξόφληση Τεχνικού

#### Διάγραμμα

![Διάγραμμα ακολουθίας - Εξόφληση Τεχνικού](uml/requirements/uc7-sequence-diagram.png)

### ΠΧ 8 - Αξιολόγηση Τεχνικού

#### Διάγραμμα

![Διάγραμμα ακολουθίας - Αξιολόγηση Τεχνικού](uml/requirements/uc8-sequence-diagram.png)

### Code coverage

Μπορείτε να δείτε την κάλυψη των τεστ του κώδικα [εδώ](coverage/index.html).