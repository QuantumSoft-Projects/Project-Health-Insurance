export interface Doctor {
    doctorId?: number;
    name: string;
    specialization: string;
    email: string;
    phone: string;
    available: boolean;
    hospital?: {
    hospitalId: number;
    name?: string;
    };
  }