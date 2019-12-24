CREATE TABLE if not exists  Mobile_MPartnerProfile(Id INT PRIMARY KEY NOT NULL,IntPartnerID   TEXT,txtTipePartner TEXT,txtPartnerCRM  TEXT, txtNamaPartner TEXT, txtChannelName TEXT, txtAlamat TEXT, intBranchID TEXT, txtBranchName TEXT, txtLongitude TEXT, txtLatitude TEXT, txtAcc TEXT) ;
CREATE TABLE if not exists  Mobile_MPartnerProfileAlias(Id INT PRIMARY KEY NOT NULL, IntPartnerID   TEXT, intPartnerIDSub   TEXT, txtTipePartner TEXT, txtPartnerCRM  TEXT, txtNamaPartner TEXT, txtChannelName TEXT, txtAlamat TEXT, intBranchID TEXT, txtBranchName TEXT, txtLongitude TEXT, txtLatitude TEXT, txtAcc TEXT);
if not exists (select 1 from sqlite_master where name = 'Mobile_trVisitPlan_Detail' and sql like 'intPartnerID' ) ALTER TABLE Mobile_trVisitPlan_Detail ADD intPartnerID TEXT;
if not exists (select 1 from sqlite_master where name = 'Mobile_trVisitPlan_Detail' and sql like 'intPartnerIDCheckIn' ) ALTER TABLE Mobile_trVisitPlan_Detail ADD intPartnerIDCheckIn TEXT;

