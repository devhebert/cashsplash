package com.example.cashsplash.services.sale;

import com.example.cashsplash.models.*;
import com.example.cashsplash.repositories.SaleItemRepository;
import com.example.cashsplash.services.campaign.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class SaleCalculationService {
    private final SaleItemRepository saleItemRepository;
    private final CampaignService campaignService;

    public BigDecimal calculateCashback(Sale sale) {
        BigDecimal totalAmount = calculateTotalAmount(sale);

        Campaign activeCampaign = campaignService.getActiveCampaign();

        if (activeCampaign != null && activeCampaign.isActive()) {
            BigDecimal cashbackPercentage = activeCampaign.getOffValue();
            BigDecimal cashback = totalAmount.multiply(cashbackPercentage);

            return formatDecimal(cashback);

        }

        return BigDecimal.ZERO;
    }

    public BigDecimal calculateTotalAmount(Sale sale) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        List<SaleItem> saleItems = saleItemRepository.findBySaleId(sale.getId());

        for (SaleItem saleItem : saleItems) {
            Product product = saleItem.getProduct();
            BigDecimal productPrice = product.getPrice();
            Integer quantity = saleItem.getAmount();

            BigDecimal itemTotal = productPrice.multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(itemTotal);
        }

        return formatDecimal(totalAmount);
    }

    private BigDecimal formatDecimal(BigDecimal value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return new BigDecimal(decimalFormat.format(value));
    }
}