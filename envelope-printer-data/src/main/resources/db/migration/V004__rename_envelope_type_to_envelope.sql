ALTER TABLE ENVELOPE_TYPE RENAME TO ENVELOPE;
ALTER TABLE ENVELOPE ALTER COLUMN TYPE RENAME TO NAME;

ALTER TABLE ENVELOPE_FIELD ALTER COLUMN ENVELOPE_TYPE_ID RENAME TO ENVELOPE_ID;
ALTER TABLE ENVELOPE_FIELD RENAME CONSTRAINT ENVELOPE_FIELD_ENVELOPE_TYPE_ID_FK TO ENVELOPE_FIELD_ENVELOPE_ID_FK;
ALTER TABLE CLIENT_GROUP ALTER COLUMN ENVELOPE_TYPE_ID RENAME TO ENVELOPE_ID;