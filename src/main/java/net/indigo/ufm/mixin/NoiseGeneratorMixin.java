package net.indigo.ufm.mixin;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.class_1779;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;

@Mixin(NoiseGenerator.class)
public class NoiseGeneratorMixin{
    @Shadow @Final private int field_111;
	@Shadow @Final private class_1779[] field_7557;

	/**
	 * @author Indigo227
	 * @reason Removal of the Farlands Patch
	 */
	@Overwrite()
	public double[] method_122(double[] ds, int i, int j, int k, int l, int m, int n, double d, double e, double f) {
		if (ds == null) {
			ds = new double[l * m * n];
		} else {
            Arrays.fill(ds, 0.0);
		}

		double g = 1.0;

		for (int p = 0; p < this.field_111; p++) {
			double h = i * g * d;
			double q = j * g * e;
			double r = k * g * f;
			long s = MathHelper.lfloor(h);
			long t = MathHelper.lfloor(r);
			h -= s;
			r -= t;
			h += s;
			r += t;
			this.field_7557[p].method_6577(ds, h, q, r, l, m, n, d * g, e * g, f * g, g);
			g /= 2.0;
		}

		return ds;
	}
}
