package leetcode.day06;

import java.util.*;

// Day 06: Validate Coupons
// Topic: String Validation, Sorting, HashSet
// Time: O(n log n)
// Space: O(n)

public class ValidateCoupons_3606{

    public List<String> validateCoupons(String[] code,
                                        String[] businessLine,
                                        boolean[] isActive) {

        List<Integer> validIndices = new ArrayList<>();

        Set<String> allowedBusinessLines = new HashSet<>(
                Arrays.asList("electronics", "grocery", "pharmacy", "restaurant")
        );

        // Filter valid coupons
        for (int i = 0; i < code.length; i++) {
            if (isActive[i]
                    && allowedBusinessLines.contains(businessLine[i])
                    && isValidCouponCode(code[i])) {
                validIndices.add(i);
            }
        }

        // Sort by business line, then coupon code
        validIndices.sort((i1, i2) -> {
            int cmp = businessLine[i1].compareTo(businessLine[i2]);
            if (cmp != 0) return cmp;
            return code[i1].compareTo(code[i2]);
        });

        // Build result
        List<String> sortedCouponCodes = new ArrayList<>();
        for (int index : validIndices) {
            sortedCouponCodes.add(code[index]);
        }

        return sortedCouponCodes;
    }

    private boolean isValidCouponCode(String couponCode) {
        if (couponCode.isEmpty()) {
            return false;
        }

        for (char ch : couponCode.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                return false;
            }
        }
        return true;
    }
}
