package com.azaroff.projects.craftsman.webapp.utils.validate.impl;

import com.azaroff.projects.craftsman.webapp.model.Customer;
import com.azaroff.projects.craftsman.webapp.utils.validate.*;
import com.azaroff.projects.craftsman.webapp.utils.validate.constant.SupportedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ValidateHelper {

    @Autowired
    private ValidateHelper validateHelper;
    @Autowired
    private ValidateBusinessDay vbd;
    @Autowired
    private ValidateSupportedCustomer vsc;
    @Autowired
    private ValidateSupportedStyle vss;
    @Autowired
    private ValidateCurrencyISO vciso;
    @Autowired
    private ValidateByProduct vp;

    /**
     * @param params is trade of list
     * @return failed records of list which have not product equels Spot or Forward
     */
    public Map<String, List<Customer>> validateValueDateByProduct(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " - Not Value Date for Spot or Forward",
                        m -> m.getValue().stream()
                                .filter(c -> {
                                    return !isValidateString(c.getType(),
                                            n -> vp.isSpotOrForward((String) n));
                                })
                                .filter(c -> c.getValueDate() == null)
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params is trade of list
     * @return failed records of list where Value Date is before Trade Date
     */
    public Map<String, List<Customer>> validateValueDate(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " - Value Date is before Trade Date",
                        m -> m.getValue().stream()
                                .filter(c -> c.getValueDate() != null)
                                .peek(c -> {
                                    c.setValueDateDate(LocalDate.parse(c.getValueDate()));
                                    c.setTradeDateDate(LocalDate.parse(c.getTradeDate()));
                                })
                                .filter(c -> c.getValueDateDate().isBefore(c.getTradeDateDate()))
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params is trade of list
     * @return failed records of list where trade was on work off day
     */
    public Map<String, List<Customer>> validateBusinessDay(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " - Not Business Days",
                        m -> m.getValue().stream()
                                .filter(c -> c.getValueDate() != null)
                                .peek(c -> {
                                    c.setValueDateDate(LocalDate.parse(c.getValueDate()));
                                    c.setTradeDateDate(LocalDate.parse(c.getTradeDate()));
                                })
                                .filter(c -> {
                                    return !isValidateDate(c.getValueDateDate(),
                                            ld -> vbd.isBusinessDay((LocalDate) ld));
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params is trade of list
     * @return failed records of list which have not supported Customer field
     */
    public Map<String, List<Customer>> validateSupportedCustomer(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " Not Suported Customer",
                        m -> m.getValue().stream()
                                .filter(c -> {
                                    return !isValidateString(c.getCustomer(),
                                            n -> vsc.isSupportedCustomer((String) n));
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params is trade of list
     * @return failed record of list where currency is not valid by ISO 4217
     */
    public Map<String, List<Customer>> validateISO(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " Not Suported Currency",
                        m -> m.getValue().stream()
                                .filter(c -> {
                                    return !isValidateString(c.getCcyPair(),
                                            n -> vciso.isSupportedIsoCode((String) n));
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params is trade of list
     * @return record of list which have Style field
     */
    public Map<String, List<Customer>> validateStyle(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey(),
                        m -> m.getValue().stream()
                                .filter(c -> c.getStyle() != null)
                                .filter(c -> {
                                    return validateHelper.isValidateString(c.getStyle(),
                                            n -> vss.isSupportedStyle((String) n));
                                })
                                .peek(c -> {
                                    c.setExpiryDateDate(LocalDate.parse(c.getExpiryDate()));
                                    c.setDeliveryDateDate(LocalDate.parse(c.getDeliveryDate()));
                                    c.setPremiumDateDate(LocalDate.parse(c.getPremiumDate()));
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params is trade of list
     * @return failed records of list where expiry date and premium date shall be before delivery date
     */
    public Map<String, List<Customer>> validateDatesOfStyle(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " - delivery date shall be after expiry date and premium date",
                        m -> m.getValue().stream()
                                .filter(c -> !c.getDeliveryDateDate().isAfter(c.getExpiryDateDate()) ||
                                        !c.getDeliveryDateDate().isAfter(c.getPremiumDateDate()))
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params is trade of list
     * @return failed records of list where American style have the excerciseStartDate,
     * which is not after the trade date and is not before the expiry date
     */
    public Map<String, List<Customer>> validateDateOfAmericanStyle(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " - American style: ExcerciseStartDate < trade date or > Expiry date",
                        m -> m.getValue().stream()
                                .filter(c -> c.getStyle().equals(SupportedStyle.AMERICAN.name()))
                                .peek(c -> {
                                    c.setExcerciseStartDateDate(LocalDate.parse(c.getExcerciseStartDate()));
                                    c.setTradeDateDate(LocalDate.parse(c.getTradeDate()));
                                })
                                .filter(c -> !c.getExcerciseStartDateDate().isAfter(c.getTradeDateDate()) ||
                                        !c.getExcerciseStartDateDate().isBefore(c.getExpiryDateDate()))
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    public boolean isValidateDate(LocalDate cal, Validate vbd) {
        return vbd.isValid(cal);
    }

    public boolean isValidateString(String str, Validate vbd) {
        return vbd.isValid(str);
    }

}
