# ΠΧ1. Εγγραφή Πελάτη

**Πρωτεύων Actor**: Πελάτης  
**Ενδιαφερόμενοι**
**Πελάτης**: Θέλει να εγγραφεί στην εφαρμογή για να μπορεί να πραγματοποιήσει ραντεβού.
**Προϋποθέσεις**: Ο *πελάτης* ενδιαφέρεται να καλέσει τεχνικό.

## Βασική Ροή

### Α) Προσθήκη στοιχείων

1. Ο *πελάτης* εισάγει τα στοιχεία του: όνομα, επώνυμο, email, τηλέφωνο, διεύθυνση, ταχυδρομικό κωδικό, κωδικό πρόσβασης και επαλήθευση κωδικού πρόσβασης.
2. Το σύστημα ελέγχει ότι ο κωδικός πρόσβασης και η επαλήθευση κωδικού ταιριάζουν.

**Εναλλακτικές Ροές**

*2α. Οι κωδικοί δεν ταιριάζουν.*  
1. Το σύστημα εμφανίζει μήνυμα σφάλματος και η περίπτωση χρήσης επανέρχεται στο πρώτο βήμα.

### Β) Επιβεβαίωση email

1. Το σύστημα στέλνει email επιβεβαίωσης στον χρήστη.
2. Ο χρήστης μέσω του email επιβεβαιώνει τον λογαριασμό του.
3. Το σύστημα αποθηκέυει τον νέο λογαριασμό.

**Εναλλακτικές Ροές**

*2α. Ο χρήστης δεν επιβεβαιώνει το email.*
1. Η περίπτωση χρήσης τερματίζει.

## Διαγράμματα

![Διάγραμμα ακολουθίας](uml/requirements/uc1-activity-diagram.png)