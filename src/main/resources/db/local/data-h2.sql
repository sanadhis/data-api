INSERT INTO DIALOG (ID, DIALOG_ID, CUSTOMER_ID, TEXT, LANGUAGE, CONSENT, TIMESTAMP)
VALUES(HIBERNATE_SEQUENCE.nextval, 1, 1, 'this is chat', 'EN', True, SYSTIMESTAMP),
 (HIBERNATE_SEQUENCE.nextval, 2, 2, 'this is 2nd chat', 'EN', NULL, SYSTIMESTAMP),
 (HIBERNATE_SEQUENCE.nextval, 3, 3, 'Je parle en francais', 'FR', False, SYSTIMESTAMP),
 (HIBERNATE_SEQUENCE.nextval, 4, 1, 'Je suis content', 'FR', True, SYSTIMESTAMP),
 (HIBERNATE_SEQUENCE.nextval, 5, 1, 'Hey how are you', 'EN', True, SYSTIMESTAMP);